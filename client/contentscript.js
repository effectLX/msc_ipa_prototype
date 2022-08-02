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
    console.log(value);
    let list=[];
    chrome.storage.local.get(['details'], function(result) {
      console.log(result)
      if(result.details){
        list=JSON.parse(result.details); 
        list.push({"key":generateRandom(4),"value":value,"Timestamp":new Date()})
        chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
          console.log('Value is set to ' + value);
        });
      }
    })
}
});

function generateRandom() {
  var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',
      result=""
  for (var i = 4; i > 0; --i)
      result += chars[Math.round(Math.random() * (chars.length - 1))]
  return result
}






