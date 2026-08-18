import type { Config } from "@level-ci/cli";

export default {
  organization: "ivan-haliurov-7-userway-org",
  project: "demo-selenium-project",
  token: process.env.LEVEL_CI_TOKEN,
  reportPaths: ["./level-ci/level-ci-reports"],
} satisfies Config;
