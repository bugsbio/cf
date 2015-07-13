(ns cf.route-53
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype record-set
  "AWS::Route53::RecordSet"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-route53-recordset.html")

(defresourcetype record-set-group
  "AWS::Route53::RecordSetGroup"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-route53-recordsetgroup.html")

(defresourcetype health-check
  "AWS::Route53::HealthCheck"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-route53-healthcheck.html")
