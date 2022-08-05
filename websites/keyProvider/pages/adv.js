import Head from "next/head";
import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import {useEffect} from "react";
import moment from "moment";

const name = 'Advertiser';

export default function Home() {

    useEffect(() => {
        var data={type: "FETCH", text:"localhost:3000"};
        window.postMessage(data, "http://localhost:3000");

        window.addEventListener("message", async event => {
            if (event.source !== window)
                return;

            // Receive match key from client
            if (event.data.type && (event.data.type === "RETURN_KEY")) {
                const encryptedMatchKey = event.data.text;
                registerEventWithAdtech(encryptedMatchKey);
            }
        })
    })

    return (
        <div className={styles.container}>
            <Head>
                <link rel="icon" href="../images/icon.png" />
                <title>{name}</title>
            </Head>
            <header className={styles.header}>
                <h1 className={utilStyles.heading2Xl}>{name}</h1>
                <h3 className={utilStyles.lightText}>Trigger event registered.</h3>
            </header>
        </div>
    );
}

function registerEventWithAdtech(encryptedMatchKey){

    // TODO: make POST to publisher flexible

    let type = "TRIGGER";
    let time = moment().toISOString();
    let event = {"type":type, "matchKey": encryptedMatchKey, "timestamp": time}

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(event)
    };
    fetch('http://localhost:8080/createEvent', requestOptions)
        .then(async response => {
            const isJson = response.headers.get('content-type')?.includes('application/json');
            const data = isJson && await response.json();

            // check for error response
            if (!response.ok) {
                // get error message from body or default to response status
                const error = (data && data.message) || response.status;
                return Promise.reject(error);
            }
        })
        .catch(error => {
            console.error('There was an error!', error);
        });

}
