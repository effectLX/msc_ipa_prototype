setInterval(removeValues,1000);

// Automatically delete all values after 24hours
function removeValues(){
  let list=[];
  try{
  chrome.storage.local.get(['details'], function(result) {
    if(result.details!=[]&&result.details){
      list=JSON.parse(result.details); 
      
    for(let index=list.length-1;index>=0;index--){
      console.log((list[index]))
      if(diff_hours(new Date(),new Date(list[index].Timestamp))>=24){
        console.log(index)
        list.splice(index,1);
      }
     }
      chrome.storage.local.set({"details":JSON.stringify(list)}, function() {
        console.log('Value is set to ' + JSON.stringify(list));
      });
    }
  })
  }
  catch(err){
    console.log(err);
  }
}

function diff_hours(dt2, dt1) {
  var diff =(dt2.getTime() - dt1.getTime()) / 1000;
  diff /= (60 * 60);
  return Math.abs(Math.round(diff));
}

// function generateRandom() {
//   var chars = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ',
//       result=""
//   for (var i = 4; i > 0; --i)
//       result += chars[Math.round(Math.random() * (chars.length - 1))]
//   return result
// }
