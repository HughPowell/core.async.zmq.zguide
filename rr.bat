echo "Starting broker..."
start lein run-rrbroker

echo "Starting workers..."
for /l %%x in (1, 1, 3) do start lein run-rrworker

echo "Starting clients..."
for /l %%x in (1, 1, 3) do start lein run-rrclient
