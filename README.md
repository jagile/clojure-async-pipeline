# clojure-async-pipeline

Test clojure async library, especially multi-threaded worker via pipeline.

```
lein run
```

Run leiningen and look on your console. You should see parallel execution
of blocks. Adjust src/dummy/clore.clj for more conrurrency.

Integration test should prove that at most 2 parallel "requests" are issued and
there is a case that exactly 2 parallel "requests" are ongoing.


## License

Copyright © 2016 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
