import Navbar from "../components/Navbar";
import Head from "next/head";
import { UserProvider } from "@auth0/nextjs-auth0";
import favicon from "../public/favicon.ico";
import "../styles/global.css";

export default function App({ Component, pageProps }) {
  return (
    <UserProvider>
      <Head>
        <link rel="shortcut icon" href={favicon.src} type="image/x-icon" />
        <title>IPA Prototype</title>
      </Head>
      <Navbar />
      <Component {...pageProps} />;
    </UserProvider>
  );
}
