(ns cf.stack)

(defonce
  ^{:private true
    :doc "Stack-fns that are created with `defstack`
         or registered with `register-stack` are stored in this atom."}
  stacks
  (atom {}))

(defn register-stack
  "Register a Cloudformation stack creating function.
  `stack-key` should be a keyword, and `stack-fn` a no-argument function that
  returns a map valid according to Cloudformation's schema - this will later be
  converted to JSON.

  Want to pass arguments to your stack? Stack functions accept no arguments on
  the basis that command line aruments passed to the stack at creation time
  would likely not be checked into source control, making it harder to be sure
  that what you have in version control = what you have in production. If you
  need two very similar, parameterized stacks, create and register two stack
  functions with different keys, and have them call shared functions with the
  different arguments internally."
  [stack-key stack-fn]
  (swap! stacks assoc stack-key stack-fn))

(defn generate-stack
  "Retrieve the stack function registered to the given key, and call it,
  returning the resulting Cloudformation stack, which should be a Clojure map."
  [stack-key & args]
  (if-let [stack (get @stacks stack-key)]
    (apply stack args)
    (throw (ex-info "Stack not found"
                    {:requested (keyword stack-key)
                     :available (keys @stacks)}))))
