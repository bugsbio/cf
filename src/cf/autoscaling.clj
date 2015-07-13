(ns cf.autoscaling
  (:require
    [cf.resource :refer [defresourcetype]]))

(defresourcetype autoscaling-group
  "AWS::AutoScaling::AutoScalingGroup"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-as-group.html")

(defresourcetype launch-configuration
  "AWS::AutoScaling::LaunchConfiguration"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-as-launchconfig.html")

(defresourcetype lifecycle-hook
  "AWS::AutoScaling::LifecycleHook"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-as-lifecyclehook.html")

(defresourcetype scaling-policy
  "AWS::AutoScaling::ScalingPolicy"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-as-policy.html")

(defresourcetype scheduled-action
  "AWS::AutoScaling::ScheduledAction"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-as-scheduledaction.html")
