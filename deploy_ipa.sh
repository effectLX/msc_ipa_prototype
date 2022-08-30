#!/bin/bash
cd kubernetes/
kubectl apply -f Config.yaml -f Deployment.yaml -f Service.yaml -f Ingress.yaml -f ManagedCertificate.yaml -f BackendConfig.yaml
kubectl set env deployment adtech DATE=$()
kubectl set env deployment matchkeyprovider DATE=$()
kubectl set env deployment mpc DATE=$()
kubectl set env deployment leader DATE=$()
kubectl set env deployment helperone DATE=$()
kubectl set env deployment helpertwo DATE=$()
kubectl set env deployment simulation DATE=$()
