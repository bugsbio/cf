(ns cf.elb
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype load-balancer
  "AWS::ElasticLoadBalancing::LoadBalancer"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-elb.html")
