window.addEventListener("message", async event => {

  // Accept messages from this web context only
  if (event.source !== window)
    return;

  // Fetch match key from client
  if (event.data.type && (event.data.type === "FETCH")) {
    let matchKey;

    // DEPLOYED USE
    // let publicKey = await getPublicKey("https://mpc.v3e.org/requestPublicKey");
    // let encryptionFactor = await getEncryptionFactor("https://mpc.v3e.org/requestPublicEncryptionFactor");

    // LOCAL USE
    let publicKey = await getPublicKey("http://localhost:8080/requestPublicKey");
    let encryptionFactor = await getEncryptionFactor("http://localhost:8080/requestPublicEncryptionFactor");

    let list=[];
    chrome.storage.local.get(['details'], async result => {
      if(result.details){
        list=JSON.parse(result.details);

        list.forEach((element) => {
          if(element.key === event.data.text) {
            matchKey = Number(element.value);
          }});

        if(!matchKey){
          matchKey = Math.floor(Math.random() * 100 + 1);
        }
            console.log('Match Key fetched: ' + matchKey);
            let encryptionData =  await getEncryptionData(publicKey, encryptionFactor, matchKey);
            await sendEncryptionData(encryptionData);
      }
    })
  }

  // Write match key to client
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
          if(element.key === event.source.origin){
            element.value = String(value);
            element.Timestamp = new Date();
            bool = false;
          }
        }

        if (bool) {
          list.push({"key":event.source.origin + "/","value":value,"Timestamp":new Date()})
        }

        chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
          console.log('Value is set to ' + value);
          alert("Match Key '" + value + "' set to the client from current website.")
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

async function getEncryptionFactor(url) {
  let res = await fetch(url);
  let data = await res.json();
  return data.publicEncryptionFactor;
}

async function getEncryptionData(publicKey, encryptionFactor, matchKey) {
  let randomSecret = Math.floor(Math.random() * 4 + 1);
  let clientKey = Math.pow(encryptionFactor, randomSecret);
  let encryptedMatchKey = matchKey * Math.pow(publicKey, randomSecret);
  return [clientKey, encryptedMatchKey]
}

async  function sendEncryptionData(encryptionData) {

  let clientKey = encryptionData[0];
  let encryptedMatchKey = encryptionData[1];
  let encryptionDataJSON = {"clientKey":clientKey, "encryptedMatchKey": encryptedMatchKey}

  let data={type: "RETURN_KEY", text:JSON.stringify(encryptionDataJSON)};
  window.postMessage(data, "*");
  alert("Match key '" + encryptedMatchKey + "' fetched from current website.")
}