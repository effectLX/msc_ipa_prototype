import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import styles from "../components/layout.module.css";
import utilStyles from "../styles/utils.module.css";

const name = 'Advertiser #1';

export default function Home() {
    return (
        <div className={styles.container}>
            <Head>
                <link rel="icon" href="../images/ic_icon.jpg" />
                <title>{name}</title>
            </Head>
            <header className={styles.header}>
                {(
                    <>
                        <h1 className={utilStyles.heading2Xl}>{name}</h1>
                        <Link href={"advertiser1_thankyou"}>
                            <a>
                                <Image
                                    priority
                                    src="/images/ic_night.jpg"
                                    height={1080}
                                    width={1920}
                                    alt={name}
                                />
                            </a>
                        </Link>
                        <div className={styles.subtitle}>
                            <h3 className={utilStyles.lightText}>This is the product preview.</h3>
                        </div>
                    </>
                )}
            </header>
        </div>
    );
}