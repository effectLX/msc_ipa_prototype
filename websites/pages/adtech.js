// PAGE ONLY FOR SIMULATION
// It is not intended that the adtech would reveal its events publicly

import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import { Button } from "@material-ui/core";
import EventTable from "../components/EventTable";
import { useState } from "react";

const urlToRequestAttribution =
  process.env.NEXT_PUBLIC_ADTECH_URL + "/checkAndSendEvents";
const urlToGetLastAttributionResult =
  process.env.NEXT_PUBLIC_ADTECH_URL + "/getLastAttributionResult";

const Adtech = () => {
  const [result, setResult] = useState(0);
  const name = "Adtech";

  const getLastAttributionResult = () => {
    fetch(urlToGetLastAttributionResult)
      .then((response) => response.json())
      .then((data) => setResult(data.result))
      .catch((error) => console.error(error));
  };

  const requestAttribution = () => {
    fetch(urlToRequestAttribution).catch((error) => console.error(error));
    getLastAttributionResult();
  };

  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <h1 className={utilStyles.heading2Xl}>{name}</h1>
        <p className={utilStyles.listItem}>(Website for demonstration only)</p>
        <h3 className={utilStyles.lightText}>
          Stores events and requests attribution.
        </h3>
        <Button
          onClick={requestAttribution}
          className="button"
          variant="outlined"
        >
          Request attribution
        </Button> {""}
        <h3 className={utilStyles.lightText}>
          Attributions from latest request: {result}
        </h3>
        <Button
          onClick={getLastAttributionResult}
          className="button"
          variant="outlined"
        >
          Get result
        </Button>
        <EventTable />
      </header>
    </div>
  );
};

export default Adtech;
