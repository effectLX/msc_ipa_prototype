import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import { useEffect } from "react";
import moment from "moment";
import {Button} from "@material-ui/core";

const name = "Publisher";

const urlToPostMessage = process.env.NEXT_PUBLIC_BASE_URL;
const urlToFetch = process.env.NEXT_PUBLIC_ADTECH_URL + "/createEvent";

const Pub = () => {
  useEffect(() => {
    window.addEventListener("message", handleMessageEvent);
    return () => {
      window.removeEventListener("message", handleMessageEvent);
    };
  }, []);

  const handleMessageEvent = async (event) => {
    if (event.source !== window) return;

    // Receive match key from client
    if (event.data.type && event.data.type === "RETURN_KEY") {
      const encryptedData = event.data.text;
      registerEventWithAdtech(encryptedData);
    }
  };

  let registerEventWithAdtech = (encryptedData) => {
    let data = JSON.parse(encryptedData);

    let event = {
      type: "SOURCE",
      matchKey: data.encryptedMatchKey,
      clientKey: data.clientKey,
      timestamp: moment().toISOString(),
    };

    const requestOptions = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(event),
    };

    fetch(urlToFetch, requestOptions).catch((error) => {
      console.error(error);
    });
  };

  let sendEvent = () => {
    let data = { type: "FETCH", text: urlToPostMessage };
    window.postMessage(data, "*");
  };

  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <h1 className={utilStyles.heading2Xl}>{name}</h1>
        <h3 className={utilStyles.lightText}>
          Registers source events and sends them to adtech.
        </h3>
        <Button className="button" variant="outlined" type="submit" onClick={sendEvent}>Register source event</Button>
      </header>
    </div>
  );
};

export default Pub;
