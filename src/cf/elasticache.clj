(ns cf.elasticache
  (:require
    [cf.ref      :as ref]
    [cf.resource :refer [defresourcetype]]))

(defresourcetype cache-cluster
  "AWS::ElastiCache::CacheCluster"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-elasticache-cache-cluster.html")
