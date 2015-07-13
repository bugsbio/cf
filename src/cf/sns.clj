(ns cf.sns
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype topic
  "AWS::SNS::Topic"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-sns-topic.html")
