(ns cf.sqs
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype queue
  "AWS::SQS::Queue"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-sqs-queues.html")

(defresourcetype queue-policy
  "AWS::SQS::QueuePolicy"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-sqs-policy.html")
