window.addEventListener("message", async event => {

  // Accept messages from this web context only
  if (event.source !== window)
    return;

  // Fetch match key from client
  if (event.data.type && (event.data.type === "FETCH")) {
    let publicKey = await getPublicKey("http://localhost:8080/requestPublicKey");
    let matchKey;

    let list=[];
    chrome.storage.local.get(['details'], async result => {
      if(result.details){
        list=JSON.parse(result.details);
        list.forEach(async element => {
          if(element.key === event.data.text){
            matchKey = Number(element.value);
          }
        });
        let encryptedMatchKey = await encryptMatchKey(publicKey, matchKey);
        await sendEncryptedMatchKey(encryptedMatchKey);
      }
    })
  }

  // Write match key to client
  // TODO: double load from match key provider domain
  if (event.data.type && (event.data.type === "SET")) {
    let value=event.data.text;
    let intValue = Number(value)
    if (!Number.isInteger(intValue) || Number.isNaN(intValue)) {
      alert('The provided match key is no integer.');
      return;
    }

    let list=[];
    chrome.storage.local.get(['details'], function(result) {
      if(result.details){
        list=JSON.parse(result.details);

        let bool = true;
        for (const element of list) {
          if(element.key === event.source.location.host){
            element.value = String(value);
            element.Timestamp = new Date();
            bool = false;
          }
        }

        if (bool) {
          list.push({"key":event.source.location.host,"value":value,"Timestamp":new Date()})
        }

        chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
          console.log('Value is set to ' + value);
        });
      }
    })
  }
});

async function getPublicKey(url) {
  let res = await fetch(url);
  let data = await res.json();
  return data.publicKey;
}

async function encryptMatchKey(publicKey, matchKey) {
  // TODO: Randomization
  if(matchKey === null){
    matchKey = Math.floor(Math.random() * 1000);
  }
  return matchKey * publicKey;
}

async  function sendEncryptedMatchKey(encryptedMatchKey) {
  var data={type: "RETURN_KEY", text:String(encryptedMatchKey)};
  window.postMessage(data, "*");
}

