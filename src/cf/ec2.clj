(ns cf.ec2
  (:require
    [cf.ref      :as ref]
    [cf.resource :refer [defresourcetype]]))

(defresourcetype instance
  "AWS::EC2::Instance"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-instance.html")

(defresourcetype elastic-ip
  "AWS::EC2::EIP"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-eip.html")

(defresourcetype volume
  "AWS::EC2::Volume"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-ebs-volume.html")

(defresourcetype volume-attachment
  "AWS::EC2::VolumeAttachment"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-ebs-volumeattachment.html")

(defn with-security-groups
  [& resource-names]
  #(assoc-in % [:Properties :SecurityGroupIds] (map ref/ref (flatten resource-names))))
