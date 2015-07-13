(ns cf.iam
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype role
  "AWS::IAM::Role"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-role.html")

(defresourcetype policy
  "AWS::IAM::Policy"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-iam-policy.html")

(defresourcetype instance-profile
  "AWS::IAM::InstanceProfile"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-iam-instanceprofile.html")

(def default
  {:AssumeRolePolicyDocument
   {:Version "2012-10-17"
    :Statement [{:Sid ""
                 :Effect "Allow"
                 :Principal {:Service "ec2.amazonaws.com"}
                 :Action "sts:AssumeRole"}]}})

(defn with-policy
  [policy-name statements]
  #(update-in % [:Properties :Policies] conj
              {:PolicyName policy-name
               :PolicyDocument {:Version "2012-10-17" :Statement statements}}))
