package com.briefauth;

import java.util.ArrayList;
import java.util.List;

public final class LoginData {
    private static final List<String> RESTRICTS = new ArrayList<>(); // ����һ���洢������������Ƶļ��ϣ�RESTRICTS ���Կ�����һ�����϶���

    public static void addPlayerName(String playerNameIn) {
        String convertedName = playerNameIn.toLowerCase(); // toLowerCase ����һ��Сд�ĸ������� String ���һ����Ա����
        if (!RESTRICTS.contains(convertedName)) { // contains ��������һ���߼�ֵ��! ���Ű�����Ϊ�෴��ֵ�������� if ���ֻ���� RESTRICTS �в��� convertedName ʱ�Ż�ִ������Ĳ���
            RESTRICTS.add(convertedName); // �� RESTRICTS �����м���ת������������
        }
    }

    public static void removePlayerName(String playerNameIn) { // ������������б����Ƴ�������Ƶķ���
        String convertedName = playerNameIn.toLowerCase(); // ��������������ת��ΪСд���Ա���д�Сд�����еıȽ�
            RESTRICTS.remove(convertedName); // �� RESTRICTS �������Ƴ�ת������������
    }

    public static boolean hasPlayerName(String playerNameIn) { // �������������б����Ƿ����ָ��������Ƶķ���
        String convertedName = playerNameIn.toLowerCase(); // ��������������ת��ΪСд���Ա���д�Сд�����еıȽ�
        return RESTRICTS.contains(convertedName); // �ж� RESTRICTS �����Ƿ����ת�����������ƣ����ؽ����ʾ�Ƿ���ڸ�����
    }

}
