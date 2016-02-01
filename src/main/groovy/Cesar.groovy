String.metaClass.prepare = { boolean upper = false,  int spacing = -1 ->
    String prepared = upper ? delegate.toUpperCase() : delegate.toLowerCase()
    if (spacing >= 0) {
        prepared = prepared.replaceAll(/\s/, '')
        if (spacing > 0) {
            prepared = prepared.toList().collate(spacing)*.join('').join(' ')
        }
    }
    prepared
}


String text = 'SYKMA OTTGB GOZPG SGOYK AJKHU TNKAX GBKIY KYINB XKYOR RKYVK XJGOZ ZUAZK YJKRG SSKLG UTATH KGASG ZOTKR RKYIG YYGOK TZRKA XIUXJ KYKTG RRGOK TZJGT YRGSU TZGMT KKZRN GAZRK RUAVR KYSGT MKGOZ TORKY IGXKY YKYJK RKAXS GZXKT ORGVK AXJAR UAVXO KTTKR KYXKZ KTGOZ IZGOZ VGXGZ ORJKY INBXK YOTJV KTJGT ZKYBU ARGTZ ZUAZV XODRK MXGTJ GOXKZ RGROH KXZ'

Character.metaClass.shift = { int decal ->
    if (delegate == ' ') return delegate
    (delegate + decal - ('A' as char)) % 26 + ('A' as char) as char
}

String.metaClass.shift = { int decal ->
    StringBuffer buff = new StringBuffer(delegate.length())
    delegate.each { c ->
        buff << (c as char).shift(decal)
    }
    buff as String
}

26.times {
    String  = text.shift(it)

}
