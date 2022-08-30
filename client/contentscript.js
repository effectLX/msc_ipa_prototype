// Event listener for every client call
window.addEventListener("message", async event => {

  if (event.source !== window)
    return;

  // Client FETCH API
  if (event.data.type && (event.data.type === "FETCH")) {
    let matchKey;

    // Deployed use only
    let publicKey = await getPublicKey("https://mpc.v3e.org/requestPublicKey");
    let encryptionFactor = await getEncryptionFactor("https://mpc.v3e.org/requestPublicEncryptionFactor");

    // local use only
    // let publicKey = await getPublicKey("http://localhost:8080/requestPublicKey");
    // let encryptionFactor = await getEncryptionFactor("http://localhost:8080/requestPublicEncryptionFactor");

    let list=[];
    chrome.storage.local.get(['details'], async result => {
      if(result.details){
        list=JSON.parse(result.details);

        list.forEach((element) => {
          if(element.key === event.data.text) {
            matchKey = Number(element.value);
          }});

        // Random match key, if client has no match key
        if(!matchKey){
          matchKey = Math.floor(Math.random() * 100 + 1);
        }
        let encryptionData =  await getEncryptionData(publicKey, encryptionFactor, matchKey);
        await sendEncryptionData(encryptionData);
      }
    })
  }

  // Client SET API
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
          list.push({"key":event.source.origin,"value":value,"Timestamp":new Date()})
        }

        chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
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
  // Assumption of client random secret between 1-5
  // See project report for detailed explanation on simplified approach to encryption
  let randomSecret = Math.floor(Math.random() * 5 + 1);
  let clientKey = Math.pow(encryptionFactor, randomSecret);
  let encryptedMatchKey = matchKey * Math.pow(publicKey, randomSecret);
  // Console log not visible in actual IPA API
  console.log("publicKey: " + publicKey + " | encFac: " + encryptionFactor + " | matchKey: " + matchKey + " | rSec: " +
      randomSecret + " | clientKey: " + clientKey + " | encMatchKey: " + encryptedMatchKey);
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