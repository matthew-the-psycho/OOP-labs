#include <iostream>
#include <iomanip>
#include <string>

using namespace std;

class LogicalOperation {
public:
    virtual string getName() {
        return "LOgetName";
    }
    virtual string getSign() {
        return "LOgetSign";
    }
    virtual int etsimate(int a, float b) {
        return 0x4655434B; //I'm sorry to sware
    }
};

class XOR : public LogicalOperation {
    public:
    string getName() override {
        return "XOR";
    }
    string getSign() override {
        return "^";
    }
    int etsimate(int a, float b) override {
        union {
            int int_member;
            float float_member;
        } kostyl;
        kostyl.float_member = b;
        return a ^ kostyl.int_member;
    }
};

class OR : public LogicalOperation {
    public:
    string getName() override {
        return "OR";
    }
    string getSign() override {
        return "|";
    }
    int etsimate(int a, float b) override {
        union {
            int int_member;
            float float_member;
        } kostyl;
        kostyl.float_member = b;
        return a | kostyl.int_member;
    }
};

//I'm sorry to use some Deutsche Schprache's analogs
//Zu english words

class UND : public LogicalOperation {
    public:
    string getName() override {
        return "UND";
    }
    string getSign() override {
        return "&";
    }
    int etsimate(int a, float b) override {
        union {
            int int_member;
            float float_member;
        } kostyl;
        kostyl.float_member = b;
        return a & kostyl.int_member;
    }
};

class NUND : public UND {
    public:
    string getName() override {
        UND kostyl;
        return "N" + kostyl.getName();
    }
    string getSign() override {
        UND kostyl;
        return "N" + kostyl.getName();
    }
    int etsimate(int a, float b) override {
        UND kostyl;
        return ~kostyl.etsimate(a, b);
    }
};

string HEX(int in) {
    stringstream ss;
    string s = "";
    ss << hex << in;
    ss >> s;
    return s;
}

string HEX(float in) {
    stringstream ss;
    string s = "";
    union {
        int mInt;
        float mFloat;
    } kostyl;
    kostyl.mFloat = in;
    ss << hex << kostyl.mInt;
    ss >> s;
    return s;
}


int main(void) {
    cout << "Klass zu polimorphyje :: BIT OPERATIONS\n";
    cout << "Entre alone int numbre:\t";
    int in;
    cin >> in;
    float un = 1.0 * float(in);
    cout << "So, bit operations zu :: {\n"
         << "    int   :: " << in << ";\n"
         << "    float :: " << un << ";\n"
         << "}\n";
    cout << "So, bit operations (hex) zu :: {\n"
         << "    int   :: " << HEX(in) << ";\n"
         << "    float :: " << HEX(un) << ";\n"
         << "}\n";
    int N = 4;
    LogicalOperation *Klasse[N] = {new XOR(), new OR(), new UND(), new NUND()};
    for (int i = 0; i < N; i++) {
        cout << "Operation zu namenn :: " << Klasse[i]->getName() << endl;
        cout << "Dec type :: " << in << ' ' << Klasse[i]->getSign() << ' ' << un << " = " << Klasse[i]->etsimate(in, un) << endl;
        cout << "HEX type :: " << HEX(in) << ' ' << Klasse[i]->getSign() << ' ' << HEX(un) << " = " << HEX(Klasse[i]->etsimate(in, un)) << endl;
        cout << endl;
    }
    return 0;
}