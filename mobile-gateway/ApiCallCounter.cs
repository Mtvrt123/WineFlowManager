namespace mobile_gateway
{
    public static class ApiCallCounter
    {
        private static readonly Dictionary<string, int> _counters = new Dictionary<string, int>();
        private static readonly object _lock = new();

        private static void Increment(string key)
        {
            lock (_lock)
            {
                if (_counters.ContainsKey(key))
                {
                    _counters[key]++;
                }
                else
                {
                    _counters[key] = 1;
                }
            }
        }

        public static void IncrementCreate() => Increment("Create");
        public static void IncrementReadAll() => Increment("ReadAll");
        public static void IncrementReadById() => Increment("ReadById");
        public static void IncrementUpdate() => Increment("Update");
        public static void IncrementDelete() => Increment("Delete");

        public static Dictionary<string, int> GetCounts()
        {
            lock (_lock)
            {
                // Return a new dictionary to avoid outside modifications
                return new Dictionary<string, int>(_counters);
            }
        }
    }

}
