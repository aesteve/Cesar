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
    char origin = delegate.isLowerCase() ? 'a' as char : 'A' as char
    ((delegate + decal - origin) % 26 + origin) as char
}

String.metaClass.shift = { int decal ->
    StringBuffer buff = new StringBuffer(delegate.length())
    delegate.each { c ->
        buff << (c as char).shift(decal)
    }
    buff as String
}

String.metaClass.usages = {
    Map usages = [:]
    delegate.each {
        Integer usage = usages[it]?: 0
        usages[it] = ++usage
    }
    usages = usages.collectEntries { String letter, int count ->
        [(letter): (count as float) / delegate.length()]
    }
    usages = usages.sort { a, b ->
        int result = 0
        if (a.value > b.value) {
            return -1
        } else if (a.value < b.value) {
            return 1
        }
        return 0
    }
    usages.remove(' ')
    usages
}

String.metaClass.mostUsed {
    delegate.usages().max({ it.value }).key
}

int candidate = (1..26).find {
    String attempt = text.shift(it)
    println attempt
    if (attempt.mostUsed() == 'E') return true
}

println "Candidate : $candidate"