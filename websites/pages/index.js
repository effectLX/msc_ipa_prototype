import styles from "../styles/layout.module.css";
import utilStyles from "../styles/utils.module.css";
import Link from "next/link";
import { Button } from "@material-ui/core";

const title = "IPA Prototype";
const subtitle = "M.Sc. Computing Individual Project";

export default function Home() {
  return (
      <div className={styles.container}>
        <header className={styles.header}>
              <h1 className={utilStyles.heading2Xl}>{title}</h1>
              <h1 className={utilStyles.headingXl}>{subtitle}</h1>
              <div>
                  <h3 className={utilStyles.lightText}>Abstract</h3>
                  <p>It is undoubtedly that online advertising has a place on the Internet, helping to finance various free-of-charge services. However, the underlying mechanisms for web tracking of users and performance measurement of advertising allow for the high vulnerability of privacy. Therefore, discussions are increasingly focused on how online advertising can work in a privacy-preserving manner. In this report, the authors look at three proposals for possible attribution systems from Apple, Google, and Meta/Mozilla designed to avoid third-party cookies. First, a summary of the current state of knowledge about the proposal development and the main differences in the technical design choices concerning privacy and utility is provided. Especially the Interoperable Private Attribution proposal offers a broad concept to balance industry and privacy interests using a server-sided multi-party computing environment (MPC) for the attribution. Second, an analysis of the IPA's attack space reveals a potential security threat to the protocol, which enables a malicious server within the MPC to leak sensitive user data. This way, the intended privacy preservation of the protocol can be bypassed. Lastly, the first functioning prototype of the IPA proposal is developed to facilitate future discussions on the concept by providing a demonstration and testing environment.</p>

                  <h3 className={utilStyles.lightText}>Source code repository</h3>
                  <Link href="https://github.com/effectLX/msc_ipa_prototype" passHref><Button className="button" variant="outlined">GitHub Repository</Button></Link>

                  <h3 className={utilStyles.lightText}>Prototype demonstration</h3>
                  <Link href="/matchkeyprovider" passHref><Button className="button" variant="outlined">Match Key Provider</Button></Link> {""}
                  <Link href="/pub" passHref><Button className="button" variant="outlined">Publisher</Button></Link> {""}
                  <Link href="/adv" passHref><Button className="button" variant="outlined">Advertiser</Button></Link> {""}
                  <Link href="/adtech" passHref><Button className="button" variant="outlined">Adtech</Button></Link>
              </div>
        </header>
      </div>
  );
}
