package com.baidu.ocr.demo;

import java.util.List;

public class ResultModle {

    /**
     * data : {"ret":[{"probability":{"average":0.998775,"min":0.996367,"variance":2.0E-6},"location":{"height":13,"left":54,"top":93,"width":22},"word_name":"姓名","word":"胡涛"},{"probability":{"average":0.999758,"min":0.999397,"variance":0},"location":{"height":12,"left":81,"top":138,"width":28},"word_name":"订单类型","word":"子母单"},{"probability":{"average":0.993548,"min":0.936449,"variance":2.75E-4},"location":{"height":11,"left":350,"top":153,"width":58},"word_name":"时间","word":"2019-07-23"},{"probability":{"average":0.990635,"min":0.647816,"variance":2.0E-4},"location":{"height":12,"left":57,"top":111,"width":71},"word_name":"单号","word":"123456789bc"},{"probability":{"average":0.992978,"min":0.938271,"variance":2.42E-4},"location":{"height":15,"left":58,"top":121,"width":139},"word_name":"地址","word":"深圳南山科技园1栋C座7楼"},{"probability":{"average":0.999536,"min":0.998855,"variance":0},"location":{"height":25,"left":184,"top":43,"width":92},"word_name":"头部","word":"顺丰运单"}],"templateSign":"f0c2605cb8c417f348987f5909634d35","templateName":"QQ截图20190723162415","scores":1,"isStructured":true,"logId":"156387317829183","clockwiseAngle":346.81}
     * error_code : 0
     * error_msg :
     */

    private DataBean data;
    private int error_code;
    private String error_msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public static class DataBean {
        /**
         * ret : [{"probability":{"average":0.998775,"min":0.996367,"variance":2.0E-6},"location":{"height":13,"left":54,"top":93,"width":22},"word_name":"姓名","word":"胡涛"},{"probability":{"average":0.999758,"min":0.999397,"variance":0},"location":{"height":12,"left":81,"top":138,"width":28},"word_name":"订单类型","word":"子母单"},{"probability":{"average":0.993548,"min":0.936449,"variance":2.75E-4},"location":{"height":11,"left":350,"top":153,"width":58},"word_name":"时间","word":"2019-07-23"},{"probability":{"average":0.990635,"min":0.647816,"variance":2.0E-4},"location":{"height":12,"left":57,"top":111,"width":71},"word_name":"单号","word":"123456789bc"},{"probability":{"average":0.992978,"min":0.938271,"variance":2.42E-4},"location":{"height":15,"left":58,"top":121,"width":139},"word_name":"地址","word":"深圳南山科技园1栋C座7楼"},{"probability":{"average":0.999536,"min":0.998855,"variance":0},"location":{"height":25,"left":184,"top":43,"width":92},"word_name":"头部","word":"顺丰运单"}]
         * templateSign : f0c2605cb8c417f348987f5909634d35
         * templateName : QQ截图20190723162415
         * scores : 1.0
         * isStructured : true
         * logId : 156387317829183
         * clockwiseAngle : 346.81
         */

        private String templateSign;
        private String templateName;
        private double scores;
        private boolean isStructured;
        private String logId;
        private double clockwiseAngle;
        private List<RetBean> ret;

        public String getTemplateSign() {
            return templateSign;
        }

        public void setTemplateSign(String templateSign) {
            this.templateSign = templateSign;
        }

        public String getTemplateName() {
            return templateName;
        }

        public void setTemplateName(String templateName) {
            this.templateName = templateName;
        }

        public double getScores() {
            return scores;
        }

        public void setScores(double scores) {
            this.scores = scores;
        }

        public boolean isIsStructured() {
            return isStructured;
        }

        public void setIsStructured(boolean isStructured) {
            this.isStructured = isStructured;
        }

        public String getLogId() {
            return logId;
        }

        public void setLogId(String logId) {
            this.logId = logId;
        }

        public double getClockwiseAngle() {
            return clockwiseAngle;
        }

        public void setClockwiseAngle(double clockwiseAngle) {
            this.clockwiseAngle = clockwiseAngle;
        }

        public List<RetBean> getRet() {
            return ret;
        }

        public void setRet(List<RetBean> ret) {
            this.ret = ret;
        }

        public static class RetBean {
            /**
             * probability : {"average":0.998775,"min":0.996367,"variance":2.0E-6}
             * location : {"height":13,"left":54,"top":93,"width":22}
             * word_name : 姓名
             * word : 胡涛
             */

            private ProbabilityBean probability;
            private LocationBean location;
            private String word_name;
            private String word;

            public ProbabilityBean getProbability() {
                return probability;
            }

            public void setProbability(ProbabilityBean probability) {
                this.probability = probability;
            }

            public LocationBean getLocation() {
                return location;
            }

            public void setLocation(LocationBean location) {
                this.location = location;
            }

            public String getWord_name() {
                return word_name;
            }

            public void setWord_name(String word_name) {
                this.word_name = word_name;
            }

            public String getWord() {
                return word;
            }

            public void setWord(String word) {
                this.word = word;
            }

            public static class ProbabilityBean {
                /**
                 * average : 0.998775
                 * min : 0.996367
                 * variance : 2.0E-6
                 */

                private double average;
                private double min;
                private double variance;

                public double getAverage() {
                    return average;
                }

                public void setAverage(double average) {
                    this.average = average;
                }

                public double getMin() {
                    return min;
                }

                public void setMin(double min) {
                    this.min = min;
                }

                public double getVariance() {
                    return variance;
                }

                public void setVariance(double variance) {
                    this.variance = variance;
                }
            }

            public static class LocationBean {
                /**
                 * height : 13
                 * left : 54
                 * top : 93
                 * width : 22
                 */

                private int height;
                private int left;
                private int top;
                private int width;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getLeft() {
                    return left;
                }

                public void setLeft(int left) {
                    this.left = left;
                }

                public int getTop() {
                    return top;
                }

                public void setTop(int top) {
                    this.top = top;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }
            }
        }
    }
}
