const fs = require("fs");
const path = require("path");
const successColor = "\x1b[32m%s\x1b[0m";
const checkSign = "\u{2705}";
const dotenv = require("dotenv").config({ path: "src/.env" });

const envFile = `export const environment = {
    PRODUCTION: ${process.env.PRODUCTION},
    PAGE_SIZE: ${process.env.PAGE_SIZE},
    API_URL: '${process.env.API_URL}',
    API_BASKET_URL: '${process.env.API_BASKET_URL}',
    KEYCLOAK_URL: '${process.env.KEYCLOAK_URL}',
    KEYCLOAK_REALM: '${process.env.KEYCLOAK_REALM}',
    KEYCLOAK_CLIENT_ID: '${process.env.KEYCLOAK_CLIENT_ID}',
    STRIPE_PUBLIC_KEY: '${process.env.STRIPE_PUBLIC_KEY}',
};
`;
const targetPath = path.join(__dirname, "./src/environments/environment.ts");
fs.writeFile(targetPath, envFile, (err) => {
  if (err) {
    console.error(err);
    throw err;
  } else {
    console.log(
      successColor,
      `${checkSign} Successfully generated environment.ts`
    );
  }
});
