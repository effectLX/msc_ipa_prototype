import Head from "next/head";
import Image from "next/image";
import styles from "../components/layout.module.css";
import utilStyles from "../styles/utils.module.css";

const name = 'Advertiser';

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
                        <Image
                            priority
                            src="/images/ic_night.jpg"
                            height={1080}
                            width={1920}
                            alt={name}
                        />
                        <div className={styles.subtitle}>
                            <h3 className={utilStyles.headingLg}>Thank you for the purchase, the product will be shipped.</h3>
                        </div>
                    </>
                )}
            </header>
        </div>
    );
}