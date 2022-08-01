import Head from "next/head";
import Image from "next/image";
import Link from "next/link";
import useSWR from 'swr'
import styles from "../components/layout.module.css";
import utilStyles from "../styles/utils.module.css";

const name = 'Publisher';

export default function Home() {
    return (
        <div className={styles.container}>
            <Head>
                <title>{name}</title>
                {/*icon of webpage*/}
            </Head>
            <header className={styles.header}>
                {(
                    <>
                        <h1 className={utilStyles.heading2Xl}>{name}</h1>
                        <Link href={"/advertiser1"}>
                            <a>
                                <Image
                                    priority
                                    src="/images/ic_day.jpg"
                                    height={1080}
                                    width={1920}
                                    alt={name}
                                />
                            </a>
                        </Link>
                        <div className={styles.subtitle}>
                            <h3 className={utilStyles.lightText}>This is advertisement.</h3>
                        </div>
                    </>
                )}
            </header>
        </div>
    );
}
