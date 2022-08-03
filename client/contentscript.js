window.addEventListener("message", function(event) {

  // Accept messages from this web context only
  if (event.source != window)
      return;

  // Fetch match key from client
  if (event.data.type && (event.data.type == "FETCH")) {
    let key = event.data.text;

      let list=[];
      chrome.storage.local.get(['details'], function(result) {
        if(result.details){
          list=JSON.parse(result.details); 
          list.forEach(element => {
            if(element.key==key){
              // let valueInt = Number.parseInt(element.value)
              // let encryptedValue = encryptMatchkey(valueInt)
              // const data = {type: "RETURN_KEY", text: encryptedValue.toString()};
              // window.postMessage(data, "http://localhost:3000/publisher");
              alert(element.value);
            }
          });
        }
      })
  }

  // Write match key to client
  // TODO: double load from match key provider domain
  if (event.data.type && (event.data.type == "SET")) {
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
          if(element.key==event.source.location.host){
            element.value = new String(value);
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






