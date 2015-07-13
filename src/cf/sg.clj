(ns cf.sg
  (:require
    [cf.ref      :as ref]
    [cf.resource :refer [defresourcetype]]))

(defresourcetype security-group
  "AWS::EC2::SecurityGroup"
  "http://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-properties-ec2-security-group.html")

(defn- security-group?
  [grantee]
  (keyword? grantee))

(defn- cidr-ip?
  [grantee]
  (and (string? grantee)
       (re-matches #"\d+\.\d+\.\d+\.\d+\/\d+" grantee)))

(def allow-all
  {:CidrIp "0.0.0.0/0" :FromPort -1 :ToPort -1 :IpProtocol -1})

(defn allow-protocol
  [protocol grantees ports]
  (for [grantee grantees port ports]
    (cond (security-group? grantee)
          {:SourceSecurityGroupId (ref/ref grantee) :FromPort port :ToPort port :IpProtocol protocol}
          (cidr-ip? grantee)
          {:CidrIp grantee :FromPort port :ToPort port :IpProtocol protocol}
          :else
          (throw (ex-info "Can't work out grantee type for security group rule" {:grantee grantee})))))

(def allow-tcp (partial allow-protocol "tcp"))
(def allow-udp (partial allow-protocol "udp"))
