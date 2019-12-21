#!/bin/bash
#shell script to stop batch jobs for DBS
kill -9 $(cat run.pid)

