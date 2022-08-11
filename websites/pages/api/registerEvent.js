try{
    const response = await fetch("http://localhost:8080/attribution", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: `{
                "type": "SOURCE",
                "matchKey": "123456789",
                "timestamp": "1"
            }`,
    })
    response.json().then(data => {
        console.log(data);
    })

} catch (e) {
    console.log("Runtime Error");
}