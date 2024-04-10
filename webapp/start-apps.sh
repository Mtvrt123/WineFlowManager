#!/bin/sh
# Start all apps in the background
cd /webapp/app1 && npm start &
cd /webapp/app2 && npm start &
cd /webapp/app3 && npm start &
cd /webapp/shell && npm start &

# Keep the container running by waiting on all background processes
wait