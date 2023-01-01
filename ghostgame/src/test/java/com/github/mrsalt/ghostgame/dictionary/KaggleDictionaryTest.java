package com.github.mrsalt.ghostgame.dictionary;

import static org.junit.Assert.*;

import org.junit.Test;


public class KaggleDictionaryTest {


    static KaggleDictionary fulldictionary = new KaggleDictionary();

    @Test
    public void loadTopWords() {
        KaggleDictionary smallVocab = new KaggleDictionary(1000);
        assertEquals("[change, changes, channel, chapter, chat, cheap, check, child, children, china, choice, choose, christmas, church]", smallVocab.wordsBeginningWith("ch").toString());
        assertEquals("[chla, chlamydia, chlamydiae, chlamydial, chlamydomonas, chlamydophila, chld, chlebowski, chlewbot, chlidonias, chlo, chloe, chloes, chlor, chloral, chlorambucil, chloramine, chloramines, chloramphenicol, chlorate, chlordane, chlordiazepoxide, chlorella, chlorenergy, chlorhexidine, chloride, chlorides, chlorinated, chlorination, chlorinator, chlorinators, chlorine, chloris, chlorite, chloro, chlorobenzene, chlorobium, chloroethane, chloroethyl, chlorofluorocarbon, chlorofluorocarbons, chloroform, chloroformate, chloroformed, chloromethane, chloromethyl, chloromycetin, chlorophenol, chlorophenols, chlorophenyl, chlorophyceae, chlorophyll, chlorophylls, chlorophyta, chloropicrin, chloroplast, chloroplasts, chloroprene, chloropropane, chloropus, chloroquine, chlorosis, chlorothalonil, chlorothiazide, chlorotic, chlorpheniramine, chlorpromazine, chlorpropamide, chlorpyrifos, chlortetracycline, chlorthalidone, chlorzoxazone, chlouber]", fulldictionary.wordsBeginningWith("chl").toString());
    }
}