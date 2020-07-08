package movieSeat;

import java.util.ArrayList;

import movieSeat.Service.SeatService;
import movieSeat.Service.SeatServiceImpl;

public class SeatObject {
	private int price; //필요
	private int people; //필요
	private String seat; // 필요
	
	public String getSeat() {
		return seat;
	}
	public void setSeat(ArrayList<String> seat) {
		String seat1= "";
		for(int i=0;i<seat.size();i++) {
			if(i==seat.size()-1) {
				seat1 += seat.get(i);
			} else {
				seat1 += seat.get(i)+",";
			}
		}
		this.seat = seat1;
		
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPeople() {
		return people;
	}
	public void setPeople(int people) {
		this.people = people;
	}
	
	
	/*
	private String A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12;
	private String B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12;
	private String C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12;
	private String D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12;
	private String E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12;
	private String F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12;
	private String G1,G2,G3,G4,G5,G6,G7,G8,G9,G10,G11,G12;
	private String H1,H2,H3,H4,H5,H6,H7,H8,H9,H10,H11,H12;
	private String I1,I2,I3,I4,I5,I6,I7,I8,I9,I10,I11,I12;
	private String J1,J2,J3,J4,J5,J6,J7,J8,J9,J10,J11,J12;
	private String K1,K2,K3,K4,K5,K6,K7,K8,K9,K10,K11,K12;
	private String L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12;


	public String getA1() {
		return A1;
	}
	public void setA1(String a1) {
		A1 = a1;
	}
	public String getA2() {
		return A2;
	}
	public void setA2(String a2) {
		A2 = a2;
	}
	public String getA3() {
		return A3;
	}
	public void setA3(String a3) {
		A3 = a3;
	}
	public String getA4() {
		return A4;
	}
	public void setA4(String a4) {
		A4 = a4;
	}
	public String getA5() {
		return A5;
	}
	public void setA5(String a5) {
		A5 = a5;
	}
	public String getA6() {
		return A6;
	}
	public void setA6(String a6) {
		A6 = a6;
	}
	public String getA7() {
		return A7;
	}
	public void setA7(String a7) {
		A7 = a7;
	}
	public String getA8() {
		return A8;
	}
	public void setA8(String a8) {
		A8 = a8;
	}
	public String getA9() {
		return A9;
	}
	public void setA9(String a9) {
		A9 = a9;
	}
	public String getA10() {
		return A10;
	}
	public void setA10(String a10) {
		A10 = a10;
	}
	public String getA11() {
		return A11;
	}
	public void setA11(String a11) {
		A11 = a11;
	}
	public String getA12() {
		return A12;
	}
	public void setA12(String a12) {
		A12 = a12;
	}
	public String getB1() {
		return B1;
	}
	public void setB1(String b1) {
		B1 = b1;
	}
	public String getB2() {
		return B2;
	}
	public void setB2(String b2) {
		B2 = b2;
	}
	public String getB3() {
		return B3;
	}
	public void setB3(String b3) {
		B3 = b3;
	}
	public String getB4() {
		return B4;
	}
	public void setB4(String b4) {
		B4 = b4;
	}
	public String getB5() {
		return B5;
	}
	public void setB5(String b5) {
		B5 = b5;
	}
	public String getB6() {
		return B6;
	}
	public void setB6(String b6) {
		B6 = b6;
	}
	public String getB7() {
		return B7;
	}
	public void setB7(String b7) {
		B7 = b7;
	}
	public String getB8() {
		return B8;
	}
	public void setB8(String b8) {
		B8 = b8;
	}
	public String getB9() {
		return B9;
	}
	public void setB9(String b9) {
		B9 = b9;
	}
	public String getB10() {
		return B10;
	}
	public void setB10(String b10) {
		B10 = b10;
	}
	public String getB11() {
		return B11;
	}
	public void setB11(String b11) {
		B11 = b11;
	}
	public String getB12() {
		return B12;
	}
	public void setB12(String b12) {
		B12 = b12;
	}
	public String getC1() {
		return C1;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public String getC2() {
		return C2;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public String getC3() {
		return C3;
	}
	public void setC3(String c3) {
		C3 = c3;
	}
	public String getC4() {
		return C4;
	}
	public void setC4(String c4) {
		C4 = c4;
	}
	public String getC5() {
		return C5;
	}
	public void setC5(String c5) {
		C5 = c5;
	}
	public String getC6() {
		return C6;
	}
	public void setC6(String c6) {
		C6 = c6;
	}
	public String getC7() {
		return C7;
	}
	public void setC7(String c7) {
		C7 = c7;
	}
	public String getC8() {
		return C8;
	}
	public void setC8(String c8) {
		C8 = c8;
	}
	public String getC9() {
		return C9;
	}
	public void setC9(String c9) {
		C9 = c9;
	}
	public String getC10() {
		return C10;
	}
	public void setC10(String c10) {
		C10 = c10;
	}
	public String getC11() {
		return C11;
	}
	public void setC11(String c11) {
		C11 = c11;
	}
	public String getC12() {
		return C12;
	}
	public void setC12(String c12) {
		C12 = c12;
	}
	public String getD1() {
		return D1;
	}
	public void setD1(String d1) {
		D1 = d1;
	}
	public String getD2() {
		return D2;
	}
	public void setD2(String d2) {
		D2 = d2;
	}
	public String getD3() {
		return D3;
	}
	public void setD3(String d3) {
		D3 = d3;
	}
	public String getD4() {
		return D4;
	}
	public void setD4(String d4) {
		D4 = d4;
	}
	public String getD5() {
		return D5;
	}
	public void setD5(String d5) {
		D5 = d5;
	}
	public String getD6() {
		return D6;
	}
	public void setD6(String d6) {
		D6 = d6;
	}
	public String getD7() {
		return D7;
	}
	public void setD7(String d7) {
		D7 = d7;
	}
	public String getD8() {
		return D8;
	}
	public void setD8(String d8) {
		D8 = d8;
	}
	public String getD9() {
		return D9;
	}
	public void setD9(String d9) {
		D9 = d9;
	}
	public String getD10() {
		return D10;
	}
	public void setD10(String d10) {
		D10 = d10;
	}
	public String getD11() {
		return D11;
	}
	public void setD11(String d11) {
		D11 = d11;
	}
	public String getD12() {
		return D12;
	}
	public void setD12(String d12) {
		D12 = d12;
	}
	public String getE1() {
		return E1;
	}
	public void setE1(String e1) {
		E1 = e1;
	}
	public String getE2() {
		return E2;
	}
	public void setE2(String e2) {
		E2 = e2;
	}
	public String getE3() {
		return E3;
	}
	public void setE3(String e3) {
		E3 = e3;
	}
	public String getE4() {
		return E4;
	}
	public void setE4(String e4) {
		E4 = e4;
	}
	public String getE5() {
		return E5;
	}
	public void setE5(String e5) {
		E5 = e5;
	}
	public String getE6() {
		return E6;
	}
	public void setE6(String e6) {
		E6 = e6;
	}
	public String getE7() {
		return E7;
	}
	public void setE7(String e7) {
		E7 = e7;
	}
	public String getE8() {
		return E8;
	}
	public void setE8(String e8) {
		E8 = e8;
	}
	public String getE9() {
		return E9;
	}
	public void setE9(String e9) {
		E9 = e9;
	}
	public String getE10() {
		return E10;
	}
	public void setE10(String e10) {
		E10 = e10;
	}
	public String getE11() {
		return E11;
	}
	public void setE11(String e11) {
		E11 = e11;
	}
	public String getE12() {
		return E12;
	}
	public void setE12(String e12) {
		E12 = e12;
	}
	public String getF1() {
		return F1;
	}
	public void setF1(String f1) {
		F1 = f1;
	}
	public String getF2() {
		return F2;
	}
	public void setF2(String f2) {
		F2 = f2;
	}
	public String getF3() {
		return F3;
	}
	public void setF3(String f3) {
		F3 = f3;
	}
	public String getF4() {
		return F4;
	}
	public void setF4(String f4) {
		F4 = f4;
	}
	public String getF5() {
		return F5;
	}
	public void setF5(String f5) {
		F5 = f5;
	}
	public String getF6() {
		return F6;
	}
	public void setF6(String f6) {
		F6 = f6;
	}
	public String getF7() {
		return F7;
	}
	public void setF7(String f7) {
		F7 = f7;
	}
	public String getF8() {
		return F8;
	}
	public void setF8(String f8) {
		F8 = f8;
	}
	public String getF9() {
		return F9;
	}
	public void setF9(String f9) {
		F9 = f9;
	}
	public String getF10() {
		return F10;
	}
	public void setF10(String f10) {
		F10 = f10;
	}
	public String getF11() {
		return F11;
	}
	public void setF11(String f11) {
		F11 = f11;
	}
	public String getF12() {
		return F12;
	}
	public void setF12(String f12) {
		F12 = f12;
	}
	public String getG1() {
		return G1;
	}
	public void setG1(String g1) {
		G1 = g1;
	}
	public String getG2() {
		return G2;
	}
	public void setG2(String g2) {
		G2 = g2;
	}
	public String getG3() {
		return G3;
	}
	public void setG3(String g3) {
		G3 = g3;
	}
	public String getG4() {
		return G4;
	}
	public void setG4(String g4) {
		G4 = g4;
	}
	public String getG5() {
		return G5;
	}
	public void setG5(String g5) {
		G5 = g5;
	}
	public String getG6() {
		return G6;
	}
	public void setG6(String g6) {
		G6 = g6;
	}
	public String getG7() {
		return G7;
	}
	public void setG7(String g7) {
		G7 = g7;
	}
	public String getG8() {
		return G8;
	}
	public void setG8(String g8) {
		G8 = g8;
	}
	public String getG9() {
		return G9;
	}
	public void setG9(String g9) {
		G9 = g9;
	}
	public String getG10() {
		return G10;
	}
	public void setG10(String g10) {
		G10 = g10;
	}
	public String getG11() {
		return G11;
	}
	public void setG11(String g11) {
		G11 = g11;
	}
	public String getG12() {
		return G12;
	}
	public void setG12(String g12) {
		G12 = g12;
	}
	public String getH1() {
		return H1;
	}
	public void setH1(String h1) {
		H1 = h1;
	}
	public String getH2() {
		return H2;
	}
	public void setH2(String h2) {
		H2 = h2;
	}
	public String getH3() {
		return H3;
	}
	public void setH3(String h3) {
		H3 = h3;
	}
	public String getH4() {
		return H4;
	}
	public void setH4(String h4) {
		H4 = h4;
	}
	public String getH5() {
		return H5;
	}
	public void setH5(String h5) {
		H5 = h5;
	}
	public String getH6() {
		return H6;
	}
	public void setH6(String h6) {
		H6 = h6;
	}
	public String getH7() {
		return H7;
	}
	public void setH7(String h7) {
		H7 = h7;
	}
	public String getH8() {
		return H8;
	}
	public void setH8(String h8) {
		H8 = h8;
	}
	public String getH9() {
		return H9;
	}
	public void setH9(String h9) {
		H9 = h9;
	}
	public String getH10() {
		return H10;
	}
	public void setH10(String h10) {
		H10 = h10;
	}
	public String getH11() {
		return H11;
	}
	public void setH11(String h11) {
		H11 = h11;
	}
	public String getH12() {
		return H12;
	}
	public void setH12(String h12) {
		H12 = h12;
	}
	public String getI1() {
		return I1;
	}
	public void setI1(String i1) {
		I1 = i1;
	}
	public String getI2() {
		return I2;
	}
	public void setI2(String i2) {
		I2 = i2;
	}
	public String getI3() {
		return I3;
	}
	public void setI3(String i3) {
		I3 = i3;
	}
	public String getI4() {
		return I4;
	}
	public void setI4(String i4) {
		I4 = i4;
	}
	public String getI5() {
		return I5;
	}
	public void setI5(String i5) {
		I5 = i5;
	}
	public String getI6() {
		return I6;
	}
	public void setI6(String i6) {
		I6 = i6;
	}
	public String getI7() {
		return I7;
	}
	public void setI7(String i7) {
		I7 = i7;
	}
	public String getI8() {
		return I8;
	}
	public void setI8(String i8) {
		I8 = i8;
	}
	public String getI9() {
		return I9;
	}
	public void setI9(String i9) {
		I9 = i9;
	}
	public String getI10() {
		return I10;
	}
	public void setI10(String i10) {
		I10 = i10;
	}
	public String getI11() {
		return I11;
	}
	public void setI11(String i11) {
		I11 = i11;
	}
	public String getI12() {
		return I12;
	}
	public void setI12(String i12) {
		I12 = i12;
	}
	public String getJ1() {
		return J1;
	}
	public void setJ1(String j1) {
		J1 = j1;
	}
	public String getJ2() {
		return J2;
	}
	public void setJ2(String j2) {
		J2 = j2;
	}
	public String getJ3() {
		return J3;
	}
	public void setJ3(String j3) {
		J3 = j3;
	}
	public String getJ4() {
		return J4;
	}
	public void setJ4(String j4) {
		J4 = j4;
	}
	public String getJ5() {
		return J5;
	}
	public void setJ5(String j5) {
		J5 = j5;
	}
	public String getJ6() {
		return J6;
	}
	public void setJ6(String j6) {
		J6 = j6;
	}
	public String getJ7() {
		return J7;
	}
	public void setJ7(String j7) {
		J7 = j7;
	}
	public String getJ8() {
		return J8;
	}
	public void setJ8(String j8) {
		J8 = j8;
	}
	public String getJ9() {
		return J9;
	}
	public void setJ9(String j9) {
		J9 = j9;
	}
	public String getJ10() {
		return J10;
	}
	public void setJ10(String j10) {
		J10 = j10;
	}
	public String getJ11() {
		return J11;
	}
	public void setJ11(String j11) {
		J11 = j11;
	}
	public String getJ12() {
		return J12;
	}
	public void setJ12(String j12) {
		J12 = j12;
	}
	public String getK1() {
		return K1;
	}
	public void setK1(String k1) {
		K1 = k1;
	}
	public String getK2() {
		return K2;
	}
	public void setK2(String k2) {
		K2 = k2;
	}
	public String getK3() {
		return K3;
	}
	public void setK3(String k3) {
		K3 = k3;
	}
	public String getK4() {
		return K4;
	}
	public void setK4(String k4) {
		K4 = k4;
	}
	public String getK5() {
		return K5;
	}
	public void setK5(String k5) {
		K5 = k5;
	}
	public String getK6() {
		return K6;
	}
	public void setK6(String k6) {
		K6 = k6;
	}
	public String getK7() {
		return K7;
	}
	public void setK7(String k7) {
		K7 = k7;
	}
	public String getK8() {
		return K8;
	}
	public void setK8(String k8) {
		K8 = k8;
	}
	public String getK9() {
		return K9;
	}
	public void setK9(String k9) {
		K9 = k9;
	}
	public String getK10() {
		return K10;
	}
	public void setK10(String k10) {
		K10 = k10;
	}
	public String getK11() {
		return K11;
	}
	public void setK11(String k11) {
		K11 = k11;
	}
	public String getK12() {
		return K12;
	}
	public void setK12(String k12) {
		K12 = k12;
	}
	public String getL1() {
		return L1;
	}
	public void setL1(String l1) {
		L1 = l1;
	}
	public String getL2() {
		return L2;
	}
	public void setL2(String l2) {
		L2 = l2;
	}
	public String getL3() {
		return L3;
	}
	public void setL3(String l3) {
		L3 = l3;
	}
	public String getL4() {
		return L4;
	}
	public void setL4(String l4) {
		L4 = l4;
	}
	public String getL5() {
		return L5;
	}
	public void setL5(String l5) {
		L5 = l5;
	}
	public String getL6() {
		return L6;
	}
	public void setL6(String l6) {
		L6 = l6;
	}
	public String getL7() {
		return L7;
	}
	public void setL7(String l7) {
		L7 = l7;
	}
	public String getL8() {
		return L8;
	}
	public void setL8(String l8) {
		L8 = l8;
	}
	public String getL9() {
		return L9;
	}
	public void setL9(String l9) {
		L9 = l9;
	}
	public String getL10() {
		return L10;
	}
	public void setL10(String l10) {
		L10 = l10;
	}
	public String getL11() {
		return L11;
	}
	public void setL11(String l11) {
		L11 = l11;
	}
	public String getL12() {
		return L12;
	}
	public void setL12(String l12) {
		L12 = l12;
	}
	*/
	
}
