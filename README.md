# IPA Prototype (M.Sc. Computing project)
This is an initial implementation of the Interoperable Private Attribution (IPA) proposal published by Mozilla and Meta in January 2022. The prototype has been developed as part of an individual project at Imperial College London (M.Sc. Computing). The project is accessible locally (explained below) or [online for demonstration](https://simulation.v3e.org/) (Note: Demonstration requires Google Chrome extension).  

## Setup and installations
#### 1. Clone the [prototype repository](https://github.com/effectLX/msc_ipa_prototype) to your local IDE
#### 2. Setup the client component (Required for deployed prototype)
The client component is a Google Chrome extension in developer mode. To set up the extension in your browser:
- Check that in the 'contentscript.js' of the client lines 12-13 are commented out and lines 16-17 are active
- Go to chrome://extensions/ and switch on developer mode
- Click on 'Load unpacked' and select the locally stored 'client' folder from the project's repository

The Chrome extension should be available in the top right of your browser window.

#### 3. Set up the website component
The websites are meant to demonstrate the IPA prototype in a real scenario. They include the logic to call the client's API to 'SET' and 'FETCH' the match key from local storage. Moreover, the adtech's use case is illustrated for the demonstration. This website is likely not visiable online and is illustrative only. To setup the websites locally from terminal:
- Type `cd websites` in the terminal from the project's root
- Install node packages by running `npm install`
- Build the npm project by running `npm run build`
- Start the npm project by running `npn run dev`

The websites should be running locally accessible at http://localhost:3000/.

#### 4. Set up the backend component
The backend includes the web servers for the match key provider, the adtech and the MPC of the IPA protocol. Especially, the MPC is relevant as it has most of the protocols specifications build in. To set up the backend locally from terminal:
- Type `cd backend` in the terminal from the project's root
- Build the Apache Maven project by running `./mvnw clean install`
- Start the Maven/ Springboot application by running `mvn spring-boot:run`

The backend should be running locally accessible at http://localhost:8080/.

## Further reading
Please refer to the [project report](https://github.com/effectLX/msc_ipa_prototype/blob/main/ProjectReport_CookieslessAttributionModelling.pdf) for further information on prototype design, usage and limitations. The latest discussions on the IPA protocol led by W3C and Meta/ Mozilla can be accessed [here](https://github.com/patcg/private-measurement).
