import {useUser} from "@auth0/nextjs-auth0";
import Head from "next/head";
import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import Link from "next/link";
import { Button } from '@material-ui/core'

const name = 'Match Key Domain';

export default function Home() {
    const {user} = useUser();

    if (user) {
        // TODO: Database on users

        // Set macht key to client
        let value = Math.floor(Math.random() * 1000);
        const data = {type: "SET", text: value.toString()};
        window.postMessage(data, "http://localhost:3000/");

        // Return page style
        return (
            <>
                <div className={styles.container}>
                    <Head>
                        <link rel="icon" href="../images/icon.png" />
                        <title>{name}</title>
                    </Head>
                    <header className={styles.header}>
                        {(
                            <>
                                <h1 className={utilStyles.heading2Xl}>{name}</h1>
                                <div className={styles.subtitle}>
                                    <h3 className={utilStyles.lightText}>Match key set to client</h3>
                                    <Link href="api/auth/logout" passHref>
                                        <Button className="button" component="a">Logout</Button>
                                    </Link>
                                </div>
                            </>
                        )}
                    </header>
                </div>
            </>
        )
    }

    return (
        <>
            <div className={styles.container}>
                <Head>
                    <link rel="icon" href="../images/icon.png" />
                    <title>{name}</title>
                </Head>
                <header className={styles.header}>
                    {(
                        <>
                            <h1 className={utilStyles.heading2Xl}>{name}</h1>
                            <div className={styles.subtitle}>
                                <h3 className={utilStyles.lightText}>User login required</h3>
                                <Link href="api/auth/login" passHref>
                                    <Button className="button" component="a">Login</Button>
                                </Link>
                            </div>
                        </>
                    )}
                </header>
            </div>
        </>
    )
}