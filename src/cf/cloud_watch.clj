(ns cf.cloud-watch
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype alarm
  "AWS::CloudWatch::Alarm"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-cw-alarm.html")
