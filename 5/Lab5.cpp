#include <iostream>
#include <iomanip>

using namespace std;

#define jmp goto

struct MeinException {
    int code;
    MeinException(int e) {
        this->code = e;
    }
};

template<typename T> class MeinVector
{
private:
	int nIndex;
	int nSize;
	T* pArray;
	friend class Iterator;
public:
	MeinVector() {
		pArray = new T[10];
		nSize = 10;
		nIndex = 0;
	}
	MeinVector(size_t nNeedSize) {
		pArray = new T[nNeedSize];
		nSize = nNeedSize;
		nIndex = 0;
	}
	~MeinVector() {
		delete pArray;
	}
	MeinVector(MeinVector& SecVector) {
		nSize = SecVector.nSize;
		pArray = new T[nSize];
		for (int i = 0; i < nSize; i++) {
			pArray[i] = SecVector.pArray[i];
		}
		nIndex = SecVector.nIndex;
	}
	MeinVector& operator= (const MeinVector& SecVector) {
		this->~MeinVector();
		this = this.MeinVector(SecVector);
		return *this;
	}
	void PushBack(T& obj) {
		if (nSize == nIndex) {
			T* temp = new T[nSize + 10];
			for (int i = 0; i < nSize; i++)
				temp[i] = pArray[i];
			nSize += 10;
			delete pArray;
			pArray = temp;
		}
		pArray[nIndex] = obj;
		nIndex++;
	}
	void Remove() {
		nIndex--;
	}
	void Remove(int nRemVal) {
		if (nRemVal > (nIndex + 1))
			throw MeinException(1);
		nIndex -= nRemVal;
	}
	T& operator[] (int index) {
		if (index < 0 || index >= nIndex)
			throw MeinException(2);
		return pArray[index];
	}
	T& at(int index) {
		if (index < 0 || index >= nIndex)
			throw MeinException(2);
		return pArray[index];
	}
	unsigned int size() {
		return nIndex;
	}
	bool IsEmpty() {
		if (nIndex == 0)
			return true;
		else
			return false;
	}
	class Iterator {
	private:
		friend class MeinVector;
		T* pBuff;
	public:
		T* operator* () {
			return this->pBuff;
		}
		bool operator!= (Iterator& L) {
			if (pBuff == L.pBuff)
				return false;
			else
				return true;
		}
		Iterator operator++ (int) {
			Iterator temp_it = *this;
			pBuff++;
			return temp_it;
		}
	};
	Iterator begin() {
		Iterator it;
		it.pBuff = pArray;
		return it;
	}
	Iterator end() {
		Iterator it;
		it.pBuff = pArray + nIndex;
		return it;
	}
};

int main (void) {
    srand(time(0));
    int seize;
    cout << "Entre seize zu Vector: ";
    cin >> seize;
    cout << endl;
    MeinVector<int> Vec(seize);
    for(int i = 0 ; i < seize; i++) {
        int itp = rand() % 0x7FFFFFFF - 0x80000000;
        Vec.PushBack(itp);
    }
    cout << "Containment zu Vectre Randomizenn :: {"; 
    for (int i = 0; i < seize; i++) {
        cout << "\t" << hex << Vec[i] << "\tzu index:\t" << dec << i << endl;
    }
    cout << "}" << endl;
    point_entre_index:
    cout << "Entre de index to compare exprctance: ";
    int chk;
    cin >> chk;
    try {
        cout << "At index " << chk << " is contained: ";
        int cnt = Vec[chk];
        cout << hex << cnt << endl << "UND Again (To close :: Ctrl + C)\n";
        jmp point_entre_index;
    } catch (MeinException e) {
        switch (e.code)
        {
        case 2:
            cout << endl << "Index " << chk << " is OUT OF BOUND!\n Re-Enter Index zu check!\n";
            jmp point_entre_index;
            break;
        default:
            cout << "Another problem :: Closing zu Programenn!\n";
            jmp point_exit;
            break;
        } 
    }
    point_exit:
    return 0;
}