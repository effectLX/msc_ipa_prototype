document.addEventListener('DOMContentLoaded', function(e) {

    let list=[];
    setValuesOnPanel().then(res=>{})

    document.getElementById("add").addEventListener("click",()=>{
        value=document.getElementById("value").value;
        chrome.storage.local.get(['details'], function(result) {
            if (result.details) {
                list = JSON.parse(result.details);
                list.push({"key": 'custom', "value": value, "Timestamp": new Date()})
                chrome.storage.local.set({"details": JSON.stringify(list)}, function () {
                    console.log('Value is set to ' + value);
                    setValuesOnPanel()
                });
            }
        })
    })

    document.getElementById("clear").addEventListener("click",()=>{
      list.splice(0,list.length);
      chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
        console.log('All values are cleared');
        setValuesOnPanel();
      });
    })
})

const myPromise = new Promise((resolve, reject) => {
  setTimeout(() => {
    resolve('Resolved');
  }, 300);
});

function setValuesOnPanel(){
  return new Promise((resolve,reject)=>{
    chrome.storage.local.get(['details'], function(result) {
      console.log('Value currently is ' + result.details);
      if(result.details){
      list=JSON.parse(result.details);
      let table=document.getElementById("tbl")
      table.innerHTML="";
      table.innerHTML='<thead><tr><th scope="col">Timestamp</th><th scope="col">Domain</th></tr></thead>'
      list.forEach(element => {
       let row=table.insertRow();
       var cell = row.insertCell();
       cell.innerHTML = element.Timestamp;
       cell = row.insertCell();
       cell.innerHTML = element.key;
       }
      )
    }});
    resolve("Data stored.")
  })
};