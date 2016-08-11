package com.example.fg.myappli;

/**
 * Created by fg on 2016/8/6.
 */

public class SubjectsBean {
    private String []academy = new String []{"通信","计算机","光电","自动化","理学院",
            "生物","经管","体育","外国语","先进制造","传媒",
            "软件","法学","半导体"};

    private String []firstSub = new String[]{"电子电路","大学物理","大学物理","大学物理","数学分析","高等数学",
            "概率论","运动解剖学",  "基础英语","工程图学","视听说","高等数学","刑法","软件设计基础"
    };

    private String[] secondSub = new String[]{"高等数学","高等数学","概率论","高等数学","高等数学","视听说",
            "高等数学","体育概论","英语语音","大学物理","读写译","离散数学","民法","线性代数",
    };

    private String[] thirdSub = new String[]{"大学物理","线性代数","工程图学","C语言","大学物理","化学","C语言",
            "健美操","英语阅读","高等数学","美术史","C++","法理","大学物理"

    };

    private double[]firstData= new double[]{62.00,40.00,54.00,45.00,49.00,45.00,42.00,56.00,44.55,55.00,
            43.00,56.00,54.90,52.00

    };
    private double[]secondData = new double[]{18.00,35.00,26.00,30.00,28.00,31.00,38.00,22.00,28.18,
            24.00,32.00,23.00,24.51,32.00
    };
    private double[]thirdData = new double[]{20.00,25.00,20.00,25.00,23.00,24.00,20.00,22.00,27.27,
            21.00,25.00,21.00,20.59,16.00
    };

    public String[] getAcademy() {
        return academy;
    }

    public double[] getFirstData() {
        return firstData;
    }

    public double[] getSecondData() {
        return secondData;
    }

    public double[] getThirdData() {
        return thirdData;
    }

    public String[] getFirstSub() {
        return firstSub;
    }

    public String[] getSecondSub() {
        return secondSub;
    }

    public String[] getThirdSub() {
        return thirdSub;
    }
}
