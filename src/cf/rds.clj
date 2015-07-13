(ns cf.rds
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype db-instance
  "AWS::RDS::DBInstance"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-rds-database-instance.html")

(defresourcetype db-security-group
  "AWS::RDS::DBSecurityGroup"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-rds-security-group.html")
