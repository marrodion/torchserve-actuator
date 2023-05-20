#!/bin/sh

REPO_ROOT=$(git rev-parse --show-toplevel)

"${REPO_ROOT}"/venv/bin/torchserve \
--start \
--model-store "${REPO_ROOT}"/scripts/integration-tests/models \
--foreground \
--models noop.mar \
--plugins-path "${REPO_ROOT}"/lib/build/libs