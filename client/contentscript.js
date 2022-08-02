window.addEventListener("message", function(event) {

  // Accept messages from this web context only
  if (event.source != window)
      return;

  // Fetch match key from client
  if (event.data.type && (event.data.type == "FETCH")) {
    let key=event.data.text;

      console.log(key);
      let list=[];
      chrome.storage.local.get(['details'], function(result) {
        if(result.details){
          list=JSON.parse(result.details); 
          list.forEach(element => {
            if(element.key==key){
              // TODO: Encrypt
              // TODO: Send to website
              alert(element.value);
            }
          });
        }
      })
  }

  // Write match key to client
  if (event.data.type && (event.data.type == "SET")) {
    let value=event.data.text;
    let list=[];
    chrome.storage.local.get(['details'], function(result) {
      if(result.details){
        list=JSON.parse(result.details);

        let bool = true;
        list.forEach(element => {
          if(element.key==event.source.location.host){
            element.value = value;
            element.Timestamp = new Date();
            console.log('Value is set to ' + value);
            bool = false;
          }
        });

        if (bool) {
          list.push({"key":event.source.location.host,"value":value,"Timestamp":new Date()})
          chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
            console.log('Value is set to ' + value);
          });
        }
      }
    })
  }
});






