package edu.jsu.mcis.cs408.crosswordmagic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.beans.PropertyChangeEvent;

import edu.jsu.mcis.cs408.crosswordmagic.controller.CrosswordMagicController;
import edu.jsu.mcis.cs408.crosswordmagic.databinding.FragmentPuzzleBinding;
import edu.jsu.mcis.cs408.crosswordmagic.view.AbstractView;
import edu.jsu.mcis.cs408.crosswordmagic.view.MainActivity;

public class PuzzleFragment extends Fragment implements AbstractView{

    private FragmentPuzzleBinding binding;
    private CrosswordMagicController controller;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPuzzleBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* get controller, register Fragment as a View */
        this.controller = ((MainActivity)getContext()).getController();
        controller.addView(this);
    }


    @Override
    public void modelPropertyChange(PropertyChangeEvent evt) {

    }
}