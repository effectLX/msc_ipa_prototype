window.addEventListener("message", function(event) {

    // Accept messages from this web context only
    if (event.source !== window)
        return;

    // Receive match key from client
    if (event.data.type && (event.data.type == "RETURN_KEY")) {
        let matchKey = event.data.text;
        alert(matchKey);
    }
})