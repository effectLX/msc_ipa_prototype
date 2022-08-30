import { useUser } from "@auth0/nextjs-auth0";
import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import Link from "next/link";
import { Button } from "@material-ui/core";
import { useEffect } from "react";

const name = "Match Key Domain";
const urlToPostMessage = process.env.NEXT_PUBLIC_BASE_URL;

export default function Home() {
const { user } = useUser();
  useEffect(async () => {
    if (user) {
      // Set macht key to client
      let matchKey = await requestMatchKey(user.email);
      await setMatchKeyToClient(matchKey);
    }
  }, [user]);

  if (user) {

    // Return page style
    return (
      <>
        <div className={styles.container}>
          <header className={styles.header}>
            {
              <>
                <h1 className={utilStyles.heading2Xl}>{name}</h1>
                <div className={styles.subtitle}>
                  <h3 className={utilStyles.lightText}>
                    Called client API to set unique match key to local storage.
                  </h3>
                  <Link href="api/auth/logout" passHref>
                    <Button className="button" variant="outlined">Logout</Button>
                  </Link>
                </div>
              </>
            }
          </header>
        </div>
      </>
    );
  }

  return (
    <>
      <div className={styles.container}>
        <header className={styles.header}>
          {
            <>
              <h1 className={utilStyles.heading2Xl}>{name}</h1>
              <div className={styles.subtitle}>
                <h3 className={utilStyles.lightText}>User login required.</h3>
                <Link href="api/auth/login" passHref>
                  <Button className="button" variant="outlined">
                    Login
                  </Button>
                </Link>
              </div>
            </>
          }
        </header>
      </div>
    </>
  );
}

function setMatchKeyToClient(matchKey) {
  const data = { type: "SET", text: matchKey };
  window.postMessage(data, urlToPostMessage);
}

async function requestMatchKey(userCredentials) {
  let matchKey;
  let urlToFetch = process.env.NEXT_PUBLIC_MATCHKEYPROVIDER_URL + "/getMatchKey";

  const requestOptions = {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userCredentials),
  };

  await fetch(urlToFetch, requestOptions)
      .then(async (response) => {
        const isJson = response.headers
        const data = isJson && (await response.json());
        matchKey = data;

        if (!response.ok) {
          const error = (data && data.message) || response.status;
          return Promise.reject(error);
        }
      })
      .catch((error) => {
        console.error("There was an error!", error);
      });

  return matchKey;
}
