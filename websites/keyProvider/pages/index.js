import {useUser} from "@auth0/nextjs-auth0";
import Head from "next/head";
import styles from "../components/layout.module.css";
import utilStyles from "../styles/utils.module.css";

const name = 'Match Key Domain';

export default function Home() {
    const {user} = useUser();

    if (user) {
        // TODO: test on user identity
        // TODO: set match key
        return (
            <>
                <a href="api/auth/logout">Logout</a>
                <div className={styles.container}>
                    <Head>
                        <link rel="icon" href="../images/ic_icon.jpg" />
                        <title>{name}</title>
                    </Head>
                    <header className={styles.header}>
                        {(
                            <>
                                <h1 className={utilStyles.heading2Xl}>{name}</h1>
                                <div className={styles.subtitle}>
                                    <h3 className={utilStyles.lightText}>Match key set to client</h3>
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
            <a href="api/auth/login">Login</a>
            <div className={styles.container}>
                <Head>
                    <link rel="icon" href="../images/ic_icon.jpg" />
                    <title>{name}</title>
                </Head>
                <header className={styles.header}>
                    {(
                        <>
                            <h1 className={utilStyles.heading2Xl}>{name}</h1>
                            <div className={styles.subtitle}>
                                <h3 className={utilStyles.lightText}>User login required</h3>
                            </div>
                        </>
                    )}
                </header>
            </div>
        </>
    )
}