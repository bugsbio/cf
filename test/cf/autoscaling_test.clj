(ns cf.autoscaling-test
  (:require
    [clojure.test :refer [deftest is testing run-tests]]
    [cf.core      :as cf]))

(defn generates
  [actual expected]
  (is (= actual expected)))

(-> (cf/manifest)
    (cf.autoscaling/launch-configuration
      :MyLaunchConfiguration
      {:AssociatePublicIpAddress true
       :IamInstanceProfile (cf/ref :MyInstanceProfile)
       :ImageId "ami-99999"
       :InstanceType :t2.medium
       :KeyName "id_rsa"
       :SecurityGroups [(cf/ref :DefaultSecurityGroup) (cf/ref :MyInstanceSecurityGroup)]})
    (cf/to-cloudformation {:pretty true})
    (generates (str "{\n"
                    "  \"Resources\" : {\n"
                    "    \"MyLaunchConfiguration\" : {\n"
                    "      \"Type\" : \"AWS::AutoScaling::LaunchConfiguration\",\n"
                    "      \"Properties\" : {\n"
                    "        \"AssociatePublicIpAddress\" : true,\n"
                    "        \"IamInstanceProfile\" : {\n"
                    "          \"Ref\" : \"MyInstanceProfile\"\n"
                    "        },\n"
                    "        \"ImageId\" : \"ami-99999\",\n"
                    "        \"InstanceType\" : \"t2.medium\",\n"
                    "        \"KeyName\" : \"id_rsa\",\n"
                    "        \"SecurityGroups\" : [ {\n"
                    "          \"Ref\" : \"DefaultSecurityGroup\"\n"
                    "        }, {\n"
                    "          \"Ref\" : \"MyInstanceSecurityGroup\"\n"
                    "        } ]\n"
                    "      }\n"
                    "    }\n"
                    "  }\n"
                    "}")))
