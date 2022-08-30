cd websites/
docker build -t "europe-west1-docker.pkg.dev/msc-ipa/ipa/ipa-frondend:latest" . && \
  docker push "europe-west1-docker.pkg.dev/msc-ipa/ipa/ipa-frondend:latest"
