echo "Starting subscribers..."
for /l %%x in (1, 1, 10) do start lein run-syncsub
echo "Starting publisher..."
lein run-syncpub